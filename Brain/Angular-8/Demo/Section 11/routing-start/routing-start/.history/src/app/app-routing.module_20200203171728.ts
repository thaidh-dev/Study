import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { UsersComponent } from "./users/users.component";
import { UserComponent } from "./users/user/user.component";
import { ServersComponent } from "./servers/servers.component";
import { EditServerComponent } from "./servers/edit-server/edit-server.component";
import { ServerComponent } from "./servers/server/server.component";
import { PageNotFoundComponent } from "./page-not-found/page-not-found.component";
import { AuthGuard } from "./auth-guard.service";

const appRoutes: Routes = [
    // không đặt pathMatch sẽ bắn ra lỗi

    // đặt prefix sẽ luôn*2 redirect vì 
    // Angular kiểm tra xem đường dẫn bạn nhập vào URL có bắt đầu = đường dẫn được chỉ định trong routes ko. 
    // Tất nhiên mọi url đều bắt đầu với '' (đó ko phải khoảng trắng, nó chỉ đơn giản là "không có gì").

    // đặt pathMath: 'full'
    // Bây giờ, bạn chỉ được chuyển hướng nếu đường dẫn đầy đủ là ''.
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    {
        path: 'users', component: UsersComponent, children: [
            { path: ':id/:name', component: UserComponent },
        ]
    },
    {
        path: 
            'servers',
            // canActivate: [AuthGuard],
            canActivateChild: [AuthGuard],
            component: ServersComponent,
            children: [
                { path: ':id/edit', component: EditServerComponent },
                { path: ':id', component: ServerComponent },
            ]
    },
    { path: 'not-found', component: PageNotFoundComponent },
    // chuyển hướng tất cả mọi thể loại, nhưng mà phải đặt ở cuối cùng
    { path: '**', redirectTo: '/not-found' },
]

@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes),
    ],
    exports: [RouterModule] // muốn sử dụng được module ở Module khác phải export
})
export class AppRoutingModule {

}