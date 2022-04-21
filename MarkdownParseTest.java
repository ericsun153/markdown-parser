import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void fileTest() throws IOException {
        ArrayList<String> links = MarkdownParse.getLinks("test-file.md");
        assertEquals(List.of("https://something.com","some-page.html"), links);
    }
}
