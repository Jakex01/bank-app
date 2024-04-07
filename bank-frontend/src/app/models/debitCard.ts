import {Card} from "./card";

export interface DebitCard extends Card {
  dailyWithdrawalLimit: number;
  transactionLimit: number;
  cashAvailable: number;
}