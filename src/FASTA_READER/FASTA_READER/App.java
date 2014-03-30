package FASTA_READER.FASTA_READER;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import org.biojava3.core.sequence.DNASequence;
import org.biojava3.core.sequence.io.FastaReaderHelper;

/**
 *
 */
public class App 
{
	private static final String TEST_DNA_SEQUENCE_HSU14574 = ">gnl|alu|HSU14574 ***ALU WARNING: Human Alu-Sx subfamily consensus sequence.\n"
			+ "GGCCGGGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGGGAGGCCGAGGCGGGCGGA\n"
			+ "TCACCTGAGGTCAGGAGTTCGAGACCAGCCTGGCCAACATGGTGAAACCCCGTCTCTACT\n"
			+ "AAAAATACAAAAATTAGCCGGGCGTGGTGGCGCGCGCCTGTAATCCCAGCTACTCGGGAG\n"
			+ "GCTGAGGCAGGAGAATCGCTTGAACCCGGGAGGCGGAGGTTGCAGTGAGCCGAGATCGCG\n"
			+ "CCACTGCACTCCAGCCTGGGCGACAGAGCGAGACTCCGTCTCAAAAAAAA\n"
			+ ">gnl|alu|HSU14573 ***ALU WARNING: Human Alu-Sq subfamily consensus sequence.\n"
			+ "GGCCGGGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGGGAGGCCGAGGCGGGTGGA\n"
			+ "TCACCTGAGGTCAGGAGTTCGAGACCAGCCTGGCCAACATGGTGAAACCCCGTCTCTACT\n"
			+ "AAAAATACAAAAATTAGCCGGGCGTGGTGGCGGGCGCCTGTAATCCCAGCTACTCGGGAG\n"
			+ "GCTGAGGCAGGAGAATCGCTTGAACCCGGGAGGCGGAGGTTGCAGTGAGCCGAGATCGCG\n"
			+ "CCACTGCACTCCAGCCTGGGCAACAAGAGCGAAACTCCGTCTCAAAAAAAA\n"
			+ ">gnl|alu|HSU14572 ***ALU WARNING: Human Alu-Sp subfamily consensus sequence.\n"
			+ "GGCCGGGCGCGGTGGCTCACGCCTGTAATCCCAGCACTTTGGGAGGCCGAGGCGGGCGGA\n"
			+ "TCACCTGAGGTCGGGAGTTCGAGACCAGCCTGACCAACATGGAGAAACCCCGTCTCTACT\n"
			+ "AAAAATACAAAAATTAGCCGGGCGTGGTGGCGCATGCCTGTAATCCCAGCTACTCGGGAG\n"
			+ "GCTGAGGCAGGAGAATCGCTTGAACCCGGGAGGCGGAGGTTGCGGTGAGCCGAGATCGCG\n"
			+ "CCATTGCACTCCAGCCTGGGCAACAAGAGCGAAACTCCGTCTCAAAAAAAA";

 
	public Map<String, DNASequence> parseFastaText(String fastaText) {

		Map<String, DNASequence> map = null;
        InputStream stream = null;
        try {

         // get sequences as byte array input stream
         stream = new ByteArrayInputStream(fastaText.getBytes("UTF-8"));

         // or get sequences from file input stream
//         stream = new FileInputStream(TestFastaReader.BASE_PATH
//           + TestFastaReader.FASTA_ALU_FILE);

         // create a map of FASTA id and the sequence content
         map = FastaReaderHelper.readFastaDNASequence(stream);

         // DNA sequences can now be accessed from the map
         for (Entry<String, DNASequence> entry : map.entrySet()) {
          String key = entry.getKey();
          String seq = entry.getValue().getSequenceAsString();
          System.out.println("key: " + key + "\nseq: " + seq);
         }

        } catch (UnsupportedEncodingException e) {
         // thrown by getBytes()
         e.printStackTrace();
        } catch (FileNotFoundException e) {
         // thrown by FileInputStream constructor
         e.printStackTrace();
        } catch (Exception e) {
         // thrown by readFastaDNASequence()
         e.printStackTrace();
        }
        return map;
        }
		
	public int findStartSequence(String dnaSequence) {

		return dnaSequence.indexOf("ATGAGT");
	}

	public int findMinus35Box(String dnaSequence) {
		return dnaSequence.indexOf("TTCAAA");
	}
	

	public int findMinus10Box(String dnaSequence) {
		return dnaSequence.indexOf("ACAAT");
	}

	public static void main( String[] args )
    {
		App app = new App();
		Map<String, DNASequence> map = app.parseFastaText(TEST_DNA_SEQUENCE_HSU14574);
		
    }
}

