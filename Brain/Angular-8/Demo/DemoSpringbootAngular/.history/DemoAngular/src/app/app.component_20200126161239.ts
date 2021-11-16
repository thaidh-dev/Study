import { Component, OnInit, ViewChildren, QueryList } from '@angular/core';
import { NhanVien } from './common/nhan-vien';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';


import { Input, Output, EventEmitter, Directive } from '@angular/core';

export type SortDirection = 'asc' | 'desc' | '';
const rotate: { [key: string]: SortDirection } = { 'asc': 'desc', 'desc': '', '': 'asc' };
export const compare = (v1, v2) => v1 < v2 ? -1 : v1 > v2 ? 1 : 0;

export interface SortEvent {
    column: string;
    direction: SortDirection;
}

@Directive({
    selector: 'th[sortable]',
    host: {
        '[class.asc]': 'direction === "asc"',
        '[class.desc]': 'direction === "desc"',
        '(click)': 'rotate()'
    }
})
export class NgbdSortableHeader {

    @Input() sortable: string;
    @Input() direction: SortDirection = '';
    @Output() sort = new EventEmitter<SortEvent>();

    rotate() {
        this.direction = rotate[this.direction];
        this.sort.emit({ column: this.sortable, direction: this.direction });
    }
}









@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  lstNhanVien: NhanVien[];
  lstNhanVienNguyenGoc: NhanVien[];

  constructor(
    private httpClient: HttpClient
  ) {

  }

  ngOnInit(): void {
    this.getListNhanVien();
  }

  getListNhanVien(): void {
    this.httpClient.get<NhanVien[]>("http://localhost:8080/TrangChu/NhanVien").subscribe(data => {
      this.lstNhanVien = data;
      this.lstNhanVienNguyenGoc = data;
    });
  }

  // sắp xếp
  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

  onSort({ column, direction }: SortEvent) {
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    if (direction === '') {
      this.lstNhanVien = this.lstNhanVienNguyenGoc;
      return;
    }

    this.lstNhanVien = this.lstNhanVienNguyenGoc.sort((a, b) => {
      const res = compare(a[column], b[column]);
      return direction === 'asc' ? res : -res;
    });
  }

}

