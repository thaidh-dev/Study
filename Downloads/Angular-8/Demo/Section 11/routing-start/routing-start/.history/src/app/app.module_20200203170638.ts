import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';


import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { UsersComponent } from './users/users.component';
import { ServersComponent } from './servers/servers.component';
import { UserComponent } from './users/user/user.component';
import { EditServerComponent } from './servers/edit-server/edit-server.component';
import { ServerComponent } from './servers/server/server.component';
import { ServersService } from './servers/servers.service';
import { Routes, RouterModule } from "@angular/router";
import { PageNotFoundComponent } from './page-not-found/page-not-found.component'
import { AppRoutingModule } from './app-routing.module';
import { AuthService } from './auth-service';
import { AuthGuard } from './auth-guard.service';

// const appRoutes: Routes = [
//   { path: '', redirectTo: '/home', pathMatch: 'full' },
//   { path: 'home', component: HomeComponent},
//   {
//     path: 'users', component: UsersComponent, children: [
//       { path: ':id/:name', component: UserComponent },
//     ]
//   },
//   {
//     path: 'servers', component: ServersComponent, children: [
//       { path: ':id/edit', component: EditServerComponent },
//       { path: ':id', component: ServerComponent },
//     ]
//   },
//   { path: 'not-found', component: PageNotFoundComponent },
//   // chuyển hướng tất cả mọi thể loại, nhưng mà phải đặt ở cuối cùng
//   { path: '**', redirectTo: '/not-found' },
// ]

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UsersComponent,
    ServersComponent,
    UserComponent,
    EditServerComponent,
    ServerComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule
    // RouterModule.forRoot(appRoutes)
  ],
  providers: [ServersService, AuthService, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
