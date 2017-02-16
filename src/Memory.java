//TODO Discuss zero page implementation


import java.util.Arrays;

public class Memory {

    private static int[] ZeroPage;
    private static int[] RAM; //0x0000-0x3FFFF This is for instructions, and the header RAM file. 32kb of information.
    private static int[] ROM; //0x
    //TODO We aren't going to implement these yet. More hardware based.
//	private static int[] VIA1;
//	private static int[] VIA2;
//	private static int[] VIA3;
//	private static int[] ACIA1;
//	private static int[] ACIA2;
//	private static int[] ACIA3;

    /*@brief Initializes a memory of size 65kb
	* 
	* @param None.
	* @return boolean True if successfully created
	*/
    //TODO Add memory check
    public boolean Memory() {
        ZeroPage = new int[0xFF]; //first 256 words
        RAM = new int[0x3F00]; //Only half of 32kb, ZeroPage conceptually is inside here. We will ignore empty space for now.
        ROM = new int[0x8000]; //
//		VIA1 = new int[0x3FFF];
//		VIA2 = new int[0x3FFF];
//		VIA3 = new int[0x3FFF];
//		ACIA1 = new int[0x3FFF];
//		ACIA2 = new int[0x3FFF];
//		ACIA3 = new int[0x3FFF];
        return true;
    }

    /*@brief Initializes a memory of size 65kb, and fills the memory with the inputted
	*         list of binary instructions.
	* @param binaryInstructions An int[] of 8-bit binary values.
	* @return boolean True if successfully created and memory is loaded
	*/
    public boolean Memory(int[] binaryInstructions) {
        RAM = Arrays.copyOf(binaryInstructions, 0x3F00);
        return true;
    }

    /*@brief Memory is loaded with the inputted binary instructions, starting
	*		  at 0x0100.
	* @param binaryInstructions An int[] of 8-bit binary values.
	* @return boolean True if successfully created and memory is loaded. False if there exists values inside the array.
	*/
    public static void setMemory(int[] binaryInstructions) {
        try {
            RAM = Arrays.copyOf(binaryInstructions, 0x3F00);
        } catch (NullPointerException e) {
            System.err.println("NullPointerException, no instructions provided. Message: " + e);
        }
    }


    /*@brief Initializes a memory of size 65kb
	* @important THIS MACHINE IS LITTLE-ENDIAN
	* @param None.
	* @return void.
	*/
    public static void write(int index, int value) {
        if (index >= 0x0000 && index <= 0x00FF) {ZeroPage[index] = value;}
        else if (index >= 0x0100 && index <= 0x3FFF) {RAM[index] = value;}
        else if (index >= 0x8000 && index <= 0xFFFF) {ROM[index] = value;}
        else {
            System.err.println("NullMemoryException, trying to access invalid memory.");
        }
    }

    /*@brief Finds the binary instruction at the supplied index
	*
	* @param index 16-bit number of index in memory
	* @return 8-bit binary instruction
	*/
    public static int read(int index) {
        if (index >= 0x0000 && index <= 0x00FF) {return ZeroPage[index];}
        if (index >= 0x0100 && index <= 0x3FFF) {return RAM[index];}
        if (index >= 0x8000 && index <= 0xFFFF) {return ROM[index];}
        else {
            System.err.println("NullMemoryException, trying to access invalid memory.");
            return -1;
        }
    }

    /*@brief Finds all binary instructions between two indexes
	*
	* @param index1 The first index of the range
	* @param index2 The second index of the range
	* @return An array of indexes between index1 and index2, inclusive.
	*/
    public static int[] readRange(int index1, int index2) {
        return null;
    }

    /*@brief Prints the array of binary instructions
	*
	* @param None.
	* @return Void.
	*/
    public String toString() {
        return Arrays.toString(RAM);
    }

    /*@brief Prints the array of binary instructions between index1 and index2.
	*
	* @param index1 The first index of the range
	* @param index2 The second index of the range
	* @return Void.
	*/
    public String toString(int index) {
        int[] tempArray = Arrays.copyOfRange(RAM, index, RAM.length);
        return Arrays.toString(tempArray);
    }

    /*@brief replace each value with zero in Memory.
	*
	* @param None.
	* @return boolean True if successful.
	*/
    public static boolean clean() {
        return true;
    }

    /*@brief enumerated type to find out what range of memory something should be placed in.
    *
    * @param None.
    * @return boolean True if in certain range.
    */
//    public static enum memoryRange {
//        inROM, inRAM, inZeroPage;
//        public boolean inRange(int val) {
//            switch (this) {
//                case inZeroPage:
//                    return (val >= 0x0000 && val <= 0x00FF);
//                case inRAM:
//                    return (val >= 0x0100 && val <= 0x3FFF);
//                case inROM:
//                    return (val >= 0x8000 && val <= 0xFFFF);
//            }
//            System.err.println("NullMemoryException, trying to access invalid memory.");
//            return false;
//        }
//
//        public static memoryRange getValue(int val) {
//            if (val >= 0x0000 && val <= 0x00FF) {return inZeroPage;}
//            if (val >= 0x0100 && val <= 0x3FFF) {return inRAM;}
//            if (val >= 0x8000 && val <= 0xFFFF) {return inROM;}
//            else {
//                System.err.println("NullMemoryException, trying to access invalid memory.");
//                return null;
//            }
//        }
//  }
}