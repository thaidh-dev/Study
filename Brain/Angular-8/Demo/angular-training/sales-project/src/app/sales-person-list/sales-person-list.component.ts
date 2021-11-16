import { Component, OnInit } from '@angular/core';
import { SalesPerson } from './sales-person';

@Component({
  selector: 'app-sales-person-list',
  templateUrl: './sales-person-list.component.html',
  styleUrls: ['./sales-person-list.component.css']
})
export class SalesPersonListComponent implements OnInit {
  salesPersonList: SalesPerson[] = [
    new SalesPerson("Anup", "Kumar", "anup@gmail.com", 50000),
    new SalesPerson("John", "Doe", "john@gmail.com", 40000),
    new SalesPerson("Claire", "Murphy", "claire@gmail.com", 90000),
    new SalesPerson("Mai", "Truong", "mai@gmail.com", 60000)
  ];

  constructor() { }

  ngOnInit() {
  }

}
