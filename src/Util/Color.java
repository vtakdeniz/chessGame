package Util;

public enum  Color {

     Call,
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

     public String getSelfColor(){

         return getColor(this);
     }

    public String getSelfAnsi(){

        return getAnsi(this);
    }



    public String getColor(Color c){

        switch (c){

            case ANSI_RED:
                return "RED";
            case ANSI_BLACK:
                return "BLACK";
            case ANSI_GREEN:
                return  "GREEN";
            case ANSI_YELLOW:
                return "YELLOW";
            case ANSI_BLUE:
                return "BLUE";
            case ANSI_PURPLE:
                return "PURPLE";
            case ANSI_CYAN:
                return "CYAN";
            case ANSI_WHITE:
                return "WHITE";

            default:
                return "WHITE";

        }


    }


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
