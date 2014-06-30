	package cl.uchile.codingtracker.operations.completions;

	import edu.illinois.codingtracker.operations.OperationLexer;
	import edu.illinois.codingtracker.operations.OperationSymbols;
	import edu.illinois.codingtracker.operations.OperationTextChunk;
	import edu.illinois.codingtracker.operations.UserOperation;

public class QuickfixUsageOperation extends UserOperation {

		protected String result;
		
		public QuickfixUsageOperation() {
			super();
		}

		public QuickfixUsageOperation(String result) {
			super();
			this.result=result;	
		}

		@Override
		public char getOperationSymbol() {
			return OperationSymbols.QUICKFIX_USAGE_SYMBOL;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void populateTextChunk(OperationTextChunk textChunk) {
			textChunk.append(result); 
		}
		
		protected void populateXMLTextChunk(OperationTextChunk textChunk) {
			textChunk.append(result); 
		}
		
		/* esto deber�a funcionar en el master
		@Override
		protected void populateXMLTextChunk(OperationTextChunk textChunk){
			textChunk.concat("QuickFixUsageOperation");
			super.populateXMLTextChunk(textChunk);
			textChunk.concat(result);
			textChunk.concat("\t" + "" + "\n"); 
			textChunk.concat("\t" + getTime() + "\n"); 
			textChunk.concat("\t" + "" + "\n");
		}
		*/
		
/*		protected void populateXMLTextChunk(OperationTextChunk textChunk) {
>>>>>>> quickfix
			textChunk.concat("<QuickfixUsageOperation>" + "\n");
			textChunk.concat("\t" + "<ContentAssistEvent>");
			textChunk.concat("" + event.assistant);
			textChunk.concat("</ContentAssistEvent>" + "\n");
			textChunk.concat("\t" + "<timestamp>");
			textChunk.concat("" + getTime());
			textChunk.concat("</timestamp>" + "\n");
			textChunk.concat("</QuickfixUsageOperation>" + "\n");
<<<<<<< HEAD
		}
=======
		}*/
		@Override
		protected void initializeFrom(OperationLexer operationLexer) {
			// TODO Auto-generated method stub

		}

		@Override
		public void replay() throws Exception {
			// TODO Auto-generated method stub

		}

	}
