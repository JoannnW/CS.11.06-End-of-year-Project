import java.io.File;
import java.util.Scanner;
public class main {
    public static void main(String[] args) throws Exception{
        Scanner scanner=new Scanner(System.in);
        //Call fileToArray methods to convert rah file into array+store:
        String fileName="Common Questions";
        String[] CQ=fileToArray(fileName);

        fileName="Common Answers";
        String[] CA=fileToArray(fileName);

        fileName="Slang Questions";
        String[] SQ=fileToArray(fileName);

        fileName="Slang answers";
        String[] SA=fileToArray(fileName);
        System.out.println("Welcome to: Common Phrases Practice (1/2)\n\nPress any key to proceed.");
        scanner.next();
        if(scanner.hasNextLine()){
            //Vocab Commons (1/2): call method
            System.out.println("------------------------------------------------------------"
                    +"\n"+"Common Phrases"+"\n"+"Review the translations carefully. \nThere'll be a quiz after each set of 3 english to ASL translations. \n\nPress any key to proceed."
                    +"\n------------------------------------------------------------");
            scanner.next();
            printQAndA(CQ, CA,0, 5);
            System.out.println("Press any key to proceed.");
            scanner.next();
            printQAndA(CQ, CA,5, 10);
            System.out.println("Press any key to proceed.");
            scanner.next();
            printQAndA(CQ, CA,10, 15);
            System.out.println("------------------------------------------------------------"+
                    "\nThat was the third phrase. A quiz will appear once you proceed.\n"+"Press any key to proceed."+
                    "\n------------------------------------------------------------");
            scanner.next();

            //Quiz Commons (3/6): call method
            System.out.println("\n\n\n\n\n\n\n\n\n"+"Quiz: Rearrange the ASL translation on each line (by copy and pasting) so that it's in order." +
                    "\nMake sure your answer has 4 lines.\n\nPress any key to continue.");
            scanner.next();
            //Random ENG phrase (out of the 3/6)
            System.out.println("ENG phrase:\n"+generateRandomEng(CQ,16));
            //Shuffled ASL translation of the ENG phrase
            printShuffleLines(CQ,CA,16);
            scanner.next();
            System.out.println("Copy and paste each line using the same format in the correct order.");
            scanner.next();
            scanner.next();
            scanner.next();
            scanner.next();
            if (scanner.hasNext(correspondingASLtoRandomEng(CQ,CA,16))){
                System.out.println("Correct!");
            }else {
                System.out.println("Incorrect..Take a look at the right answer:\n"+correspondingASLtoRandomEng(CQ,CA,16));
            }
        }else{
            System.out.println("There must've been a typing mistake. Try again.");
        }


    }
    //Count lines: no. of elements in the String array (file to be converted to an array later)
    //Count lines steps: creating file; scan the file; loop: each new line equals 1 increment in the line counter
    public static int countLinesInFile(String fileName) throws java.io.FileNotFoundException{ //throw "java...Exception" according to the bug, cuz we know the file exists
        File file=new File(fileName);
        Scanner scanner=new Scanner(file);

        int lineCounter=0;
        while(scanner.hasNextLine()){
            lineCounter++;
            scanner.nextLine();
        }
        return lineCounter;
    }
    //Convert file to array: stores stuff on each line into an element in a String array (for efficiency in retrieving those phrases later)
    //Convert file to array steps: call count line method (we need to know the no. of elements in the array); make an array, with the length being the line counter int; loop: each element is each line in the file
    public static String[] fileToArray(String fileName) throws java.io.FileNotFoundException{
        int linesInFile=countLinesInFile(fileName);
        String[] array=new String[linesInFile];

        File file=new File(fileName); //realised i have to create a file to scan for the loop: each element is each line in the file that's identified with a scanner
        Scanner scanner=new Scanner(file);

        for (int i=0;i<linesInFile;i++){
            array[i]=scanner.nextLine();
        }
        return array;
    }
    //Vocab: Print questions with answers
    public static void printQAndA(String[] ques, String[] ans, int startIndex, int endIndex){
        for (int i=startIndex;i<endIndex;i++){
            String result=ques[i]+"\n"+ans[i];
            //I don't wear there to be "Eng: "/"ASL: "before every single line, only
            if (i%5==0){
                result="------------------------------------------------------------"
                        +"\n"+"English: "+"\n"+ques[i]+"\n\n"+"ASL: "+"\n"+ans[i];
            }
            System.out.println(result);
        }
    }
    //Generate random indexes from eng arrays
    public static String generateRandomEng(String[] eng, int endIndex) {
        int randomIndex = (int) (Math.random() * (endIndex/5))*5;
        /*there was an error where if the index number is not 5, it'd create an infinite loop. (I used a while loop to ensure the number's divisible by 5)
        To do this, instead of creating an iteration, I will modify it so the method generates a random index that is a multiple of 5 directly*/
        return eng[randomIndex];
    }
    //find the corresponding asl translation to the random Eng
    public static String correspondingASLtoRandomEng(String[] eng, String[] asl, int endIndex){
        String randomEng=generateRandomEng(eng,endIndex);
        String result="";
        for(int i=0;i<asl.length;i++){
            if (eng[i].equals(randomEng)){
                result=asl[i]+"\n"+ asl[i+1]+"\n"+asl[i+2]+"\n"+asl[i+3]+"\n"+asl[i+4];
                break;
            }
        }
        return result;
    }
    //shuffle corresponding asl translation to the random Eng
    public static void printShuffleLines(String[] eng, String[] asl, int endIndex) throws java.io.FileNotFoundException{
        String originalASL=correspondingASLtoRandomEng(eng,asl,endIndex);
        //there was an error when i tried to make the output of the corres.. method an array, so here I'm turning the Spring I got from that method into an array
        //new array: split originalASL into an array of lines
        String[] lines=originalASL.split("\n");

        //new array: store shuffled lines
        String[] shuffledLines=new String[lines.length];

        //there was an error that some indices are out of bounds
        //So I'm making sure that endIndex is not greater than the number of lines available
        if (endIndex>lines.length){
            endIndex=lines.length;
        }

        for (int i=0;i<endIndex;i++){
            //random index for each line and assign it to the shuffledLines array
            int randomShuffledLines=(int)(Math.random()*lines.length);

            //Swap lines between current and random index
            String temp=lines[i];
            lines[i]=lines[randomShuffledLines];
            lines[randomShuffledLines]=temp;

            //Assign shuffled line (String) to the randomShuffledLines array
            shuffledLines[i]=lines[i];
        }
        //join the shuffled lines back into a string (for consistency)
        String shuffledString=String.join("\n",shuffledLines);
        System.out.println("\n\nASL Shuffled String:\n"+shuffledString);
    }
    //Check if the answer matches the original

/*
    //Mixed up ASL translation
    public static void printQuiz(String[] eng, String[] asl, int endIndex){
        //print random english phrase
        String randomEng=generateRandom(eng,endIndex);

    }
    //Check if the ASL answer is right
    public static int checkASLAnswer(String[] aslAnswer){
        for (int i=0;i<)
    }
 */
}
