import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class LuckyNineCardGameFFN {

   public static void main(String[] args) {

      Scanner userInput = new Scanner(System.in);
      Random randomNumber = new Random();

      // ARRAYS FOR THE DECK OF CARDS
      String[] playingCardNumbers = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "K", "Q"};
      String[] playingCardSuits = {" of Hearts♥", " of Diamonds♦", " of Spade♠", " of Clubs♣"};

      // INITIALIZATION OF SOME VARIABLES
      int userOption1 = 0, userOption2, dealerValue, dealer, score, players, numberOfPlayers, cardValue = 0;
      double userCashFund, dealerFundResult = 0.0;
      String value, totalvalue, dealerTotalValue, dealerName;

      // HashMap to store dealer names and their fund results
      HashMap<String, Double> dealerFunds = new HashMap<>();

      while (userOption1 != 3) {

         // MAIN MENU OF GAME
         System.out.println("Welcome to Lucky9");
         System.out.println("[1]Start Game");
         System.out.println("[2]View Leader board");
         System.out.println("[3]Exit");
         System.out.print("Select your action: ");

         //INPUT VERIFICATION FOR MAIN MENU
         userOption1 = 0;
         while (userOption1 < 1 || userOption1 > 3) {
            while (!userInput.hasNextInt()) {
               System.out.println("Invalid Response. Try Again. ");
               System.out.print("Select your action: ");
               userInput.next(); // Consume invalid input
            }
            userOption1 = userInput.nextInt();
         } // end of MAIN MENU WHILE VERIFICATION

         // SWITCH FOR MAIN MENU
         switch (userOption1) {
            case 1:

               System.out.print("\nEnter your dealer name: ");
               dealerName = userInput.next();

               // Check if the dealer exists in the HashMap
               if (!dealerFunds.containsKey(dealerName)) {
                  dealerFunds.put(dealerName, 0.0); // Initialize fund result for new dealer
               }

               userCashFund = dealerFunds.get(dealerName); // Get dealer's fund result

               // CHECK IF CASHFUND IS VALID AMOUNT/INPUT
               userCashFund = 0;
               while (userCashFund <= 0) {
                  System.out.print("Enter cash fund: ");
                  while (!userInput.hasNextDouble()) {
                     System.out.println("Invalid Amount. Try Again. ");
                     System.out.print("Enter cash fund: ");
                     userInput.next(); // Consume invalid input
                  }
                  userCashFund = userInput.nextDouble();
               } // END OF CASHFUND'S WHILE 

               // CHECK IF USER'S PLAYER INPUT IS VALID
               numberOfPlayers = 0;
               while (numberOfPlayers < 1 || numberOfPlayers > 20) {
                  System.out.print("Enter number of player(s): ");
                  while (!userInput.hasNextInt()) {
                     System.out.println("Invalid. Please enter a valid number.");
                     System.out.print("Enter number of player(s): ");
                     userInput.next(); // Consume invalid input
                  }
                  numberOfPlayers = userInput.nextInt();
                  if (numberOfPlayers < 1 || numberOfPlayers > 20) {
                     System.out.println("Maximum no. of players is 20, Minimum is 1. Try Again.");
                  }
               } // END OF NO. OF PLAYER'S WHILE

               //CONTAINS CARD AND ITS VALUE
               int[] playerRandomBet = new int[numberOfPlayers];
               int[] playersTotalCardValue = new int[numberOfPlayers];
               Boolean[] winningPlayer = new Boolean[numberOfPlayers];
               players = 0;

               System.out.println(); // NEW LINE

               // RANDOMIZED PLAYER'S RANDOM BET
               do {
                  int playersBet = randomNumber.nextInt(80) + 20;
                  System.out.println("Player " + (players + 1) + " bet: " + playersBet);
                  playerRandomBet[players] = playersBet;
                  players++;
               } while (numberOfPlayers != players);
               // END OF RANDOM PLAYER BET DO WHILE

               System.out.println(); // NEW LINE

               // PLAY|QUIT OPTION
               do {
                  System.out.println("[1] Play");
                  System.out.println("[2] Quit");
                  System.out.print("Select your action: ");
                  while (!userInput.hasNextInt()) {
                     System.out.println("Invalid Response. Try Again.");
                     System.out.print("Select your action: ");
                     userInput.next(); // Consume the invalid input					
                  }
                  userOption2 = userInput.nextInt();

                  // SWITCH.CASE OF PLAY, QUIT
                  int userOption2attempt = 1;
                  while (userOption2attempt > 0) {
                     switch (userOption2) {
                        case 1: // PLAY

                           cardValue = 0;

                           int randomCardNumber = randomNumber.nextInt(13) + 0;
                           int randomCardSuits = randomNumber.nextInt(4) + 0;
                           if (randomCardNumber < 10) {
                              cardValue += (randomCardNumber + 1);
                           }

                           System.out.println("Your 1st card: " + playingCardNumbers[randomCardNumber] + playingCardSuits[randomCardSuits]);
                           randomCardNumber = randomNumber.nextInt(13) + 0;
                           randomCardSuits = randomNumber.nextInt(4) + 0;
                           if (randomCardNumber < 10) {
                              cardValue += (randomCardNumber + 1);
                           }

                           System.out.println("Your 2nd card: " + playingCardNumbers[randomCardNumber] + playingCardSuits[randomCardSuits]);

                           System.out.print("Draw another card [Y/N]:");
                           char drawAnotherCard = Character.toUpperCase(userInput.next().charAt(0)); // Convert input to uppercase

                           // TO CHECK IF INPUT IS VALID 
                           while (drawAnotherCard != 'Y' && drawAnotherCard != 'N') {
                              System.out.println("Enter valid response. Only Y/N.");
                              System.out.print("Draw another card [Y/N]:  ");
                              drawAnotherCard = Character.toUpperCase(userInput.next().charAt(0)); // Convert input to uppercase
                           }

                           // YES|NO FOR DRAW ANOTHER CARD
                           switch (drawAnotherCard) {
                              case 'Y':
                                 randomCardNumber = randomNumber.nextInt(13) + 0;
                                 randomCardSuits = randomNumber.nextInt(4) + 0;
                                 if (randomCardNumber < 10) {
                                    cardValue += (randomCardNumber + 1);
                                 }
                                 System.out.println("Your 3rd card: " + playingCardNumbers[randomCardNumber] + playingCardSuits[randomCardSuits]);

                                 break;

// <------------------------------------------------------------------------>                              
                              case 'N':

                           }
                           // END OF YES|NO FOR DRAW ANOTHER CARD

                           //if two digit counts the last digit as the card's value
                           value = String.valueOf(cardValue);
                           if (value.length() > 1) {
                              char cardActualValue = value.charAt(1);
                              dealerTotalValue = String.valueOf(cardActualValue);
                              dealerValue = Integer.parseInt(dealerTotalValue);
                              System.out.println("Your total value: " + dealerValue);
                              dealer = dealerValue;
                           } else {
                              System.out.println("Your total value: " + cardValue);
                              dealer = cardValue;
                           }

                           // ENTER TO VIEW OTHER PLAYER'S CARDS
                           System.out.println(); // for new line
                           System.out.println("Please Enter to view player's cards");
                           userInput.nextLine();
							userInput.nextLine();
                           //VIEW OTHER PLAYERS CARD
                           players = 0;
                           do {
                              players++;
                              cardValue = 0;
                              System.out.println();
                              System.out.println("Player " + players);
                              System.out.println();

                              randomCardNumber = randomNumber.nextInt(13) + 0;
                              randomCardSuits = randomNumber.nextInt(4) + 0;
                              if (randomCardNumber < 10) {
                                 cardValue += (randomCardNumber + 1);
                              }
                              System.out.println("1st card: " + playingCardNumbers[randomCardNumber] + playingCardSuits[randomCardSuits]);
                              randomCardNumber = randomNumber.nextInt(13) + 0;
                              randomCardSuits = randomNumber.nextInt(4) + 0;
                              if (randomCardNumber < 10) {
                                 cardValue += (randomCardNumber + 1);
                              }
                              System.out.println("2nd card: " + playingCardNumbers[randomCardNumber] + playingCardSuits[randomCardSuits]);

                              //adds the 3rd card if value  not 9
                              if (cardValue != 9) {
                                 randomCardNumber = randomNumber.nextInt(13) + 0;
                                 randomCardSuits = randomNumber.nextInt(4) + 0;
                                 if (randomCardNumber < 10) {
                                    cardValue += (randomCardNumber + 1);
                                 }
                                 System.out.println("3rd card: " + playingCardNumbers[randomCardNumber] + playingCardSuits[randomCardSuits]);
                              }
							  
							  System.out.println();

                              //if two digit counts the last digit as the card's value
                              value = String.valueOf(cardValue);
                              if (value.length() > 1) {
                                 char cardActualValue = value.charAt(1);
                                 totalvalue = String.valueOf(cardActualValue);
                                 int cardValue1 = Integer.parseInt(totalvalue);
                                 System.out.println("Player " + players + " value: " + cardValue1);
                                 playersTotalCardValue[players - 1] = cardValue1;
                                 score = cardValue1;
                              } else {
                                 System.out.println("Player " + players + " value: " + cardValue);
                                 playersTotalCardValue[players - 1] = cardValue;
                                 score = cardValue;
                              }
							  
							  

                              //check the possibility of winning
                              if (score == 9) {
                                 winningPlayer[players - 1] = true;
                              } else if (score > dealer) {
                                 winningPlayer[players - 1] = true;
                              } else if (score <= dealer) {
                                 winningPlayer[players - 1] = false;
                              }

                              System.out.println("----------------------------------");
                           } while (numberOfPlayers != players);

                           //evaluation of the winners & losers
                           System.out.println();
                           System.out.println("Winner(s):");
                           players = 0;
                           do {
                              players++;
                              if (winningPlayer[players - 1] == true) {
                                 System.out.println("Player " + players + "(" + (playerRandomBet[players - 1] * 3) + ")");

                              }
                           } while (numberOfPlayers != players);
                           System.out.println();
                           System.out.println("Loser(s):");
                           players = 0;
                           do {
                              players++;
                              if (winningPlayer[players - 1] == false) {
                                 System.out.println("Player " + players);
                              }
                           } while (numberOfPlayers != players);
                           System.out.println();

                           System.out.println("----------------------------------");

                           // DEALER RESULT EVALUATION
                           System.out.println();
                           System.out.println("Dealer Result");
                           System.out.println();

                           // YUNG WIN NG DEALER AY YUNG BETS NG LOSER
                           players = 0;
                           int totalLoserBet = 0;
                           do {
                              players++;
                              if (winningPlayer[players - 1] == false) {
                                 int currentPlayerBet = playerRandomBet[players - 1];
                                 totalLoserBet += currentPlayerBet;
                              }
                           } while (numberOfPlayers != players);
                           System.out.println("Win: " + totalLoserBet);

                           // YUNG LOSS NG DEALER AY YUNG WINNINGS NG WINNERS NA NAKATRIPLE NA
                           players = 0;
                           int totalWinnerBet = 0;
                           // Variable to accumulate the total bet for winners
                           
                           do {
                              players++;

                              if (winningPlayer[players - 1] == true) {
                                 int currentPlayerBet = playerRandomBet[players - 1] * 3;
                                 totalWinnerBet += currentPlayerBet;
                              }
                           } while (numberOfPlayers != players);
                           System.out.println("LOSS : " + totalWinnerBet);
                           System.out.println();

                           double dealerFund = userCashFund - totalWinnerBet;
                           dealerFundResult = dealerFund + totalLoserBet;
                           System.out.println("Dealer total fund: " + dealerFundResult);
                           System.out.println();

                           System.out.println("----------------------------------");

                           System.out.println();
                           System.out.println("[1] Play");
                           System.out.println("[2] Quit");
                           System.out.print("Select your action: ");
                           while (!userInput.hasNextInt()) {
                              System.out.println("Invalid Response. Try Again.");
                              System.out.print("Select your action: ");
                              userInput.next(); // Consume the invalid input					
                           }
                           userOption2 = userInput.nextInt();
                           System.out.println();

                           dealerFunds.put(dealerName, dealerFundResult);

                           break;

// <------------------------------------------------------------------------>                            
                        case 2: // QUIT
                           System.out.println();
                           System.out.println("You Quit.");
                           userOption2attempt = 0; // TO EXIT THE LOOP
                           System.out.println();
                           System.out.println("----------------------------------");
                           System.out.println();
                           break;

// <------------------------------------------------------------------------>                           
                        default:
                           System.out.println("default:");

                     } // end of case1- play & case2-quit

                  } // end of SWITCH.CASE OF PLAY, QUIT

               } while (userOption2 >= 3);
               // END OF PLAY|QUIT OPTION

               break;

// <------------------------------------------------------------------------>               
            case 2:
               System.out.println();

               List<Map.Entry<String, Double>> sortedDealers = new ArrayList<>(dealerFunds.entrySet());
               sortedDealers.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

               // Display the leaderboard
               System.out.println("Leader Board (TOP 10)");
               System.out.println("----------------------------------");
               System.out.println();
               int rank = 1;
               for (Map.Entry<String, Double> entry : sortedDealers) {
                  System.out.println(rank + ". " + entry.getKey() + " (" + entry.getValue() + ")");
                  rank++;
                  if (rank > 10) {
                     break; // Display only top 10 dealers
                  }
               }
               System.out.println();

               break;

// <------------------------------------------------------------------------>                
            case 3:
               System.out.println();
               System.out.println("Goodbye!");
               break;

            default:
               System.out.println("default: ");

         } // end of 1st switch

      } // end of 1st while 

   } // end of method

} // end of class

