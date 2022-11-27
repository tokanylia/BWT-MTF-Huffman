package practice.work;

public class Main {

    public static void main(String[] args) {
        BWT bwt = new BWT();
        MTF mtf = new MTF();
        Huffman huffman = new Huffman();

        String text = "Huffman coding is a lossless data compression algorithm.\nThe idea is to assign variable-length codes to input characters,\nlengths of the assigned codes are based on the frequencies of corresponding characters.\nThe most frequent character gets the smallest code and the least frequent character gets the largest code.";

        String bwtData = bwt.encode(text);
        String alphabet = mtf.getAlphabet(bwtData);
        String mtfData = mtf.encode(bwtData, alphabet);
        String dataBWT_MTF_Huffman = huffman.encode(mtfData);

        String decodedDataHuffman = huffman.decode(dataBWT_MTF_Huffman);
        String decodedDataMtf = mtf.decode(decodedDataHuffman, alphabet);
        String decodedData = bwt.decode(decodedDataMtf);
        System.out.println("Decoded text: " + decodedData);

        int normalSize = text.length() * 8;
        int compressedSizeBWT_MTF_Huffman = dataBWT_MTF_Huffman.length();
        double rate = 100.0 - (compressedSizeBWT_MTF_Huffman * 100.0 / normalSize);

        System.out.println("Normal size: " + normalSize);
        System.out.println("Compressed size by BWT->MTF->Huffman: " + compressedSizeBWT_MTF_Huffman);
        System.out.printf("Compressed by BWT->MTF->Huffman is %.2f%% smaller than the original. %n", rate);
    }
}
