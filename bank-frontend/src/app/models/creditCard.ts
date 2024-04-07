import {Card} from "./card";

export interface CreditCard extends Card {
  creditLimit: number;
  moneyTaken: number;
  nextPaymentDate: string;

}