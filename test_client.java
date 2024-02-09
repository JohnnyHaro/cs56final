public class ClientTest {
    //create tests for Client
    @Test
    public void testClientSendsInstructionsToServer() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        //we will choose random instructions to test them
        String[] instructions = {
            "add#John",
            "add#Alice",
            "remove#John",
            "add#Bob",     
            "close"
        };
       
        for (String instruction : instructions) {
            Client.sendMessage(instruction);
        }

   
        String output = outputStream.toString();
        assertTrue(output.contains("add#John"));
        assertTrue(output.contains("add#Alice"));
        assertTrue(output.contains("remove#John"));
        assertTrue(output.contains("add#Bob"));
        assertTrue(output.contains("close"));
    }
}



