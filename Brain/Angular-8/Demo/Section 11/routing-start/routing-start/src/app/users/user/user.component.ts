import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs/internal/Subscription';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit, OnDestroy {
  user: { id: number, name: string };
  paramsSubscription: Subscription;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    // route sẽ lấy cái url hiện tại và dùng cái snapshot để lấy param của cái url đấy
    this.user = {
      id: this.route.snapshot.params['id'],
      name: this.route.snapshot.params['name']
    };

    this.paramsSubscription = this.route.params.subscribe((params: Params) => {
      // .id cho nhanh, cồng kềnh vl
      this.user.id = params.id;
      this.user.name = params['name'];
    });

  }

  ngOnDestroy(): void {
    this.paramsSubscription.unsubscribe();
  }

}
