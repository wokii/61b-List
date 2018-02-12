public class OffByN implements CharacterComparator{
    public int offByWhat;
    public OffByN(int N){
        super();
        offByWhat = N;
    }
    public boolean equalChars(char x, char y){

        int dif = Math.abs(x - y);
        if(dif == offByWhat){
            return true;
        } else{
            return false;
        }
    }
}
