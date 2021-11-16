import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ViewChild } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

// mới thêm
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatSidenavModule, MatFormField, MatFormFieldModule, MatOptionModule, MatOption, MatInputModule, MatRippleModule, MatAutocompleteModule, MatInput, MatAutocompleteTrigger, MatSelectModule, MatAccordion, MatExpansionModule, MatExpansionPanel, MatExpansionPanelHeader } from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    // mới thêm
    BrowserAnimationsModule,
    MatButtonModule,
    FormsModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatAutocompleteModule,
    MatInputModule,
    MatOptionModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatExpansionModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
