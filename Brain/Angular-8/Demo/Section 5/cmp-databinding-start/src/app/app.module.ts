import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';


import { AppComponent } from './app.component';
import { AppChildComponent } from './app-child/app-child.component';
import { ProjectContentComponent } from './project-content/project-content.component';

@NgModule({
  declarations: [
    AppComponent,
    AppChildComponent,
    ProjectContentComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
