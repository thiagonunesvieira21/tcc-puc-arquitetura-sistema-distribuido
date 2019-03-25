package ejb;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.ibm.ctg.client.ECIRequest;
import com.ibm.ctg.client.JavaGateway;

/**
 * Exemplos de implementação acessando uma transaçãoo CICS utilizando o 
 * CICS Transaction Gateway (CTG 8.1) no z/OS
 * 
 */
public class TesteMainframe {

	// Declaracao de objetos padrao que serao utilizados na integracao.
	private static JavaGateway javaGatewayObject;
	private static InputStreamReader isr = new InputStreamReader(System.in);
	private static BufferedReader input = new BufferedReader(isr);
	private static int iValidationFailed = 0;

	public static void main(String[] args) {

		try {

	        System.setProperty("javax.net.ssl.trustStrore", "C:\\Program Files\\Java\\jdk1.8.0_144\\jre\\lib\\security\\cacerts");
	        System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
			
			// Declaracao e inicializacao de variaveis e do objeto ECIRequest.
			ECIRequest eciRequestObject = null;

			// Endereco IP do servidor CICS.
			String strUrl = "10.0.75.2";
			
			// Porta utilizada para conexao com o CICS Server.
			int iPort = 1435;
			
			javaGatewayObject = new JavaGateway(strUrl, iPort);//, "THIAGO", "12345678");
//					strUrl, iPort);
			// Transacao CICS que sera acessada.
			String strProgram = "ADDS";
			
			// Servidor CICS onde faremos a conexao.
			String strChosenServer = "CICSUNIX";
			
			// Tamanho da area de comunicacao utilizada entre o programa COBOL e o Java.
			int iCommareaSize = 60;
			
			// Contem os dados da area de comunicacao que serao transmitidos do programa COBOL para o Java.
			byte[] abytCommarea = new byte[iCommareaSize];
			
			// Contem os dados que serao informados ao programa CICS via area de comunicacao.
			String strCommarea = "00001";
			
			/* Realiza a copia da String de parametro(strCommarea) que sera transmitida ao programa 
			 * COBOL para a area de comunicacao(COMMAREA). Copia realizada atraves do metodo System.arraycopy.
			 */
			System.arraycopy(strCommarea.getBytes("CP1047"),
					0, abytCommarea,
					0, strCommarea.length());
			
			
			// Seta os parametros do construtor do objeto ECIRequest.
			eciRequestObject =
				new ECIRequest(ECIRequest.ECI_SYNC, //Tipo de Chamada ECI.
						strChosenServer,            //Servidor CICS.
						null,                       //Usuario CICS.
						null,                       //Senha CICS.
						strProgram,                 //Programa CICS a ser executado.
						strProgram,                 //ID da transacao CICS a ser executada.
						abytCommarea,               //Array de Bytes contendo a COMMAREA.
						iCommareaSize,              //Tamanho da COMMAREA.
						ECIRequest.ECI_NO_EXTEND,   //ECI extend mode
						0);                         //ECI LUW token

			/* Chama o metodo de validacao de codigo de retorno do CICS.
			 * Em seguida exibe o valor retornado pela transacao no formato
			 * ASCII.
			 * 
			 * Se o metodo retornar true um erro de seguranca ocorreu e sera
			 * solicitada a entrada de novo usuario e senha CICS para prosse-
			 * guimento, caso contrario o programa continuara a execucao.
			 * 
			 * Caso ocorra qualquer outro erro diferente do erro de seguranca
			 * o programa e encerrado automaticamente. (System.exit(0)).
			 */
			while (validaRetorno(eciRequestObject) == true) {
				System.out.println("\nEntre com seu usuário CICS:");
				eciRequestObject.Userid = input.readLine().trim();
				System.out.println("\nEntre com sua senha CICS:");
				eciRequestObject.Password = input.readLine().trim();
				iValidationFailed++;
			}
			
			System.out.println("O Programa CICS " + strProgram + " retornou os seguintes dados: ");
			
			System.out.println("\n" + new String(eciRequestObject.Commarea, "cp1047"));

			// Fecha o objeto JavaGateway antes de sair.
			if (javaGatewayObject.isOpen() == true) {
				javaGatewayObject.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* O metodo validaRetorno determina se o retorno do acesso a transacao CICS
	 * ocorreu com sucesso ou nao atraves da validacao do codigo de retorno CICS.
	 * 
	 * Se tiver ocorrido um erro, a string do erro e exibida juntamente ao codigo
	 * de retorno antes do encerramento do programa.
	 */
	private static boolean validaRetorno(ECIRequest requestObject) {

		try {

			int iRc = javaGatewayObject.flow(requestObject);
			
			/* Realiza uma validacao no codigo de retorno do CICS.
			 * Retorna falso caso nao existam erros.
			 */
			switch (requestObject.getCicsRc()) {
			case ECIRequest.ECI_NO_ERROR:
				if (iRc == 0) {
					return false;
				} else {
					System.out.println("\nErro de Gateway (" + requestObject.getRcString() + "), corrija o erro e execute novamente.");
					if (javaGatewayObject.isOpen() == true) {
						javaGatewayObject.close();
					}
					System.exit(0);
				}

			/* Verifica se ha erros de seguranca e retorna true se a validacao
			 * falhou em quatro ou menos vezes.
			 */
			case ECIRequest.ECI_ERR_SECURITY_ERROR:
				
				if (iValidationFailed == 0) {
					return true;
				}
				
				System.out.print("\n\nFalha na Valida��o. ");
				
				if (iValidationFailed < 3) {
					System.out.println("Tente informar os dados novamente.");
					return true;
				}
				break;
				
			
			/* Verifica se foi gerado um erro onde o usu�rio esta autorizado
			 * a acessar o servidor CTG mas nao esta autorizado a executar
			 * a transacao EC01.
			 * 
			 * Caso o erro ocorra, o programa devera ser executado novamente
			 * com os dados de usuario e senha CICS autorizados a executar a
			 * transacao.
			 */
			case ECIRequest.ECI_ERR_TRANSACTION_ABEND:
				System.out.println("\nVoc� n�o esta autorizado a executar esta transa��o.");
			}
			
			System.out.println("\nECI retornado: " + requestObject.getCicsRcString());
			System.out.println("O código do erro foi: " + requestObject.Abend_Code + "\n");
			
			if (javaGatewayObject.isOpen() == true) {
				javaGatewayObject.close();
			}
			System.exit(0);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return true;
	}
}