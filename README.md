# PasswordCracker

Command Line Arguments: 

  1st argument:
    
      Brute Force = -BF (return plaintext)
    
      Dictionary = -D (return plaintext)
    
      Hashing = -H (return a hash)
     
  2nd argument: 
     
      MD5 = -M
    
      BCrypt = -B
 
      SHA256 = -S
      
   3rd argument: 
      
      Enter a hash or a password (no dash)
      *if you entered -H, put in plaintext, otherwise put in a hash*
    
Formatting: 
 
    EX: java PassCrack (space) 1st argument (space) 2nd argument (space) 3rd argument");
    Ex: java PassCrack -BF -M 0cc175b9c0f1b6a831c399e269772661
    EX: java PassCrack -H -M a
    
    
 Contributors: Vlad Gaza, Phuc Trinh 
  
 Dependencies:
  - http://www.mindrot.org/projects/jBCrypt/
  - https://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash-in-java
  - https://www.educative.io/answers/reading-the-nth-line-from-a-file-in-java
  - https://github.com/dwyl/english-words
  - https://github.com/danielmiessler/SecLists/blob/master/Passwords/Common-Credentials/10-million-password-list-top-10000.txt
  
 Notes: 
  - Rainbow Table files should be stored on a external hard drive due to the large file size 
  - We will include a Rainbow Table with the first 8 characters of plaintext passwords at a later time
  - In order to run this program you will need to create several text files:
  
      -FinalCheck.txt (put the letter "a" in it)
      -MD5Hashes.txt 
      -RainbowPasswordList.txt
      -SHA256Hashes.txt

 Bugs:
  -  the last text that was tested before the program is finished repeats twice in the files above
