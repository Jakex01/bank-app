import {Card} from "./card";

export interface PrePaidCard extends Card {
    dailyLimit: number;
    cashAvailable: number;
}