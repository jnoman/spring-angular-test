import { Article } from "./Article"

export interface Order{
    id?:Number
    reference?:String,
    createdDate?:any,
    articles?:Article[]
}