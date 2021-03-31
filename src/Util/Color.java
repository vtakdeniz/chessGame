package Util;

public enum  Color {

     ANSI_RESET ,
     ANSI_BLACK ,
     ANSI_RED ,
     ANSI_GREEN ,
     ANSI_YELLOW,
     ANSI_BLUE ,
     ANSI_PURPLE ,
     ANSI_CYAN ,
     ANSI_WHITE ,
     RESET ;

     public String getAnsi(Color c){

         switch (c){

             case ANSI_RESET:
                 return "\u001B[0m";
             case ANSI_RED:
                 return "\u001B[31m";
             case ANSI_BLACK:
                 return "\u001B[30m";
             case ANSI_GREEN:
                 return  "\u001B[32m";
             case ANSI_YELLOW:
                 return "\u001B[33m";
             case ANSI_BLUE:
                 return "\u001B[34m";
             case ANSI_PURPLE:
                 return "\u001B[35m";
             case ANSI_CYAN:
                 return "\u001B[36m";
             case ANSI_WHITE:
                 return "\u001B[37m";
             case RESET:
                 return  "\033[0m";

             default:
                 return "\033[0m";

         }


     }

}
