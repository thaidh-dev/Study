import { Component, OnInit } from '@angular/core';

@Component({
  // selector: 'app-servers',
  // selector: '[app-servers]',
  selector: '.app-servers',
  // template: `<app-server></app-server>
  //           <app-server></app-server>`,
  templateUrl: "/servers.component.html",
  styleUrls: ['./servers.component.css']
})
export class ServersComponent implements OnInit {
  allowNewServer = false;
  serverCreationStatus = 'oh no'; 
  // bắt buộc phải là '' không là nó không nhận
  serverName = 'sôsososososoo';
  servers = ['test 1', 'test 2'];

  constructor() {
    setTimeout(() => {
      this.allowNewServer = true;
    }, 500000);
  }

  ngOnInit() {
  }

  onCreateServer() {
    this.serverCreationStatus = "server đã đc create" + this.serverName;
    this.servers.push(this.serverName);
  }

  onUpdateServerName(thamSo: Event) {
    this.serverName = (<HTMLInputElement> thamSo.target).value;
  }
}
