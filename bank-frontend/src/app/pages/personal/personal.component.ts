import {AfterViewInit, Component, OnInit} from '@angular/core';
import {CurrencyPipe, DatePipe, NgClass, NgForOf, NgIf} from "@angular/common";
import {CardService} from "../../services/card-service/card.service";
import {Card} from "../../models/card";

import Swiper from "swiper";
import {OperationService} from "../../services/operation-service/operation.service";
import {Operation} from "../../models/Operation";
import {PdfService} from "../../services/pdf-service/pdf.service";

@Component({
  selector: 'app-personal',
  standalone: true,
  imports: [
    NgClass,
    CurrencyPipe,
    DatePipe,
    NgForOf,
    NgIf
  ],
  templateUrl: './personal.component.html',
  styleUrl: './personal.component.css'
})
export class PersonalComponent implements OnInit, AfterViewInit{

  cards: Card[] = [];
  operations: Operation[] = [];
  page = 0;
  size = 3;
  moreOperationsAvailable = true;
  constructor(private cardService: CardService,
              private operationService: OperationService,
              private pdfService: PdfService) {

  }

  loadMoreOperations() {
    if (this.moreOperationsAvailable) {
      this.operationService.getAllOperations(this.page, this.size).subscribe((data: Operation[]) => {
        this.operations = [...this.operations, ...data];
        console.log('Fetched operations for page ' + this.page + ':', data);

        // If fewer operations than the page size are returned, there are no more operations
        if (data.length < this.size) {
          this.moreOperationsAvailable = false;
        }

        this.page++; // Increment the page for next time "Show More" is clicked
      });
    }
  }
  ngOnInit() {
    this.cardService.getAllCreditCards().subscribe((data: Card[]) => {
      this.cards = data;
    });

    this.loadMoreOperations();
  }
  ngAfterViewInit(): void {
    new Swiper('.mySwiper', {
      pagination: {
        el: '.swiper-pagination',
        type: 'fraction',
      },
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
    });
  }



  downloadTransactionPdf(transactionId: number): void {
    this.pdfService.downloadPdf(transactionId);
  }
  selectedCard: Card | null = null;
  selectedOperation: Operation | null = null;
  toggleCardDetails(card: Card) {
    this.selectedCard = this.selectedCard === card ? null : card;
  }
  openModal(operation:Operation) {
    console.log("here")
    this.selectedOperation = operation;
    document.body.classList.add('no-scroll');
  }

  closeModal() {
    this.selectedOperation = null;
    document.body.classList.remove('no-scroll');
  }
}
