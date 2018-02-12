public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> temp = new ArrayDeque<Character>();
        for(int i = 0; i < word.length(); i++){
            temp.addLast(word.charAt(i));
        }
        return temp;
    }

    public boolean isPalindrome(String word){
        Deque<Character> w = wordToDeque(word);
        int size = w.size(); //SIZE has to be constant! or it will reduce and cause for loop to end early than EXPECTED!
        for(int i = 0; i < size / 2; i++){
            char last = w.removeLast();
            char first = w.removeFirst();
            if(first != last){
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> w = wordToDeque(word);
        int size = w.size();//SIZE has to be constant! or it will reduce and cause for loop to end early than EXPECTED!
        for(int i = 0; i < size / 2; i++){
            char last = w.removeLast();
            char first = w.removeFirst();
            if(!cc.equalChars(first, last)){
                return false;
            }
        }
        return true;
    }


}
