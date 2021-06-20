import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-child',
  templateUrl: './app-child.component.html',
  styleUrls: ['./app-child.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AppChildComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
