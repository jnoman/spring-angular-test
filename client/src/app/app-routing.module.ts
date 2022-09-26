import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddArticleComponent } from './add-article/add-article.component';
import { ArticleDetailComponent } from './article-detail/article-detail.component';
import { ArticleComponent } from './article/article.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { LoginComponent } from './login/login.component';
import { OrderComponent } from './order/order.component';

const routes: Routes = [
  {path:"login", component:LoginComponent},
  {path:"articles", component:ArticleComponent},
  {path:"inscription", component:InscriptionComponent},
  {path:"articles/add", component: AddArticleComponent},
  {path:"articles/:id", component: ArticleDetailComponent},
  {path:"orders", component: OrderComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
