import { Component, OnInit } from '@angular/core';

import { ServersService } from '../servers.service';
import { ActivatedRoute, Params, Router, Data } from '@angular/router';
import { isNumber } from 'util';

@Component({
  selector: 'app-server',
  templateUrl: './server.component.html',
  styleUrls: ['./server.component.css']
})
export class ServerComponent implements OnInit {
  server: { id: number, name: string, status: string };

  constructor(private serversService: ServersService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.route.data.subscribe((data: Data) => {
      this.server = data['server']
    });

    // const id = +this.route.snapshot.params.id;
    // this.server = this.serversService.getServer(id);
    // this.route.params.subscribe((params: Params) => {
    //   console.log(isNumber(+params.id)); // true
    //   console.log(isNumber(params.id)); // false
    //   this.server = this.serversService.getServer(+params.id);
    // })
  }

  onEdit() {
    // queryParamsHandling: giữ lại các query param (các param sau dấu ?) của url
    this.router.navigate(['edit'], { relativeTo: this.route, queryParamsHandling: 'preserve' });
  }

}
