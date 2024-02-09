public class ServerTest {

    @Test
    //testing the server side
    public void testServerReceivesMessagesFromClient() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(
            "add#John\nadd#Alice\nremove#John\nadd#Bob\nclose".getBytes()
        );
        System.setIn(inputStream);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        System.setOut(printWriter);

        Server.listenForMessages();

        String output = stringWriter.toString();
        assertTrue(output.contains("Received instruction: add#John"));
        assertTrue(output.contains("Received instruction: add#Alice"));
        assertTrue(output.contains("Received instruction: remove#John"));
        assertTrue(output.contains("Received instruction: add#Bob"));
        assertTrue(output.contains("Received instruction: close"));
    }
}
