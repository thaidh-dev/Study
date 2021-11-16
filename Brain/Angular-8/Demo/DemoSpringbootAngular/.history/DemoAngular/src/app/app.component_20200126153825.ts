import { Component, OnInit, ViewChildren, QueryList } from '@angular/core';
import { NhanVien } from './common/nhan-vien';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { SortEvent, NgbdSortableHeader } from './sortable.directive';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  lstNhanVien: NhanVien[];

  constructor(
    private httpClient: HttpClient
  ) {

  }

  ngOnInit(): void {
    this.getListNhanVien();
  }

  getListNhanVien(): void {
    this.httpClient.get<NhanVien[]>("http://localhost:8080/TrangChu/NhanVien").subscribe(data => this.lstNhanVien = data);
  }

  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

  onSort({ column, direction }: SortEvent) {

    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    if (direction === '') {
      this.countries = COUNTRIES;
      return;
    }
    
    this.countries = [...COUNTRIES].sort((a, b) => {
      const res = compare(a[column], b[column]);
      return direction === 'asc' ? res : -res;
    });
  }

}
