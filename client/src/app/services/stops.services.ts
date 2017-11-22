import { Injectable } from "@angular/core";
import { Headers, Http, Response } from "@angular/http"
import { STOPS } from "../models/stops"
import "rxjs/add/operator/map";
import { Observable } from "rxjs/Observable";
@Injectable()
export class stopsServices {
    url: String;
    constructor(private _http: Http) {
        this.url = "http://localhost:8080/server/api";

    }
    getStop() {
        return this._http.get(this.url + "/stops")
            .map(res => res.json());
    }

    saveStops(stop: STOPS) {
        let header = new Headers({ "Content-Type": "application/x-www-form-urlencoded" });
        console.log(stop);
        return this._http.post(this.url + "/stops/add", "lat="+ stop.LATITUDE +"&long=" +stop.LONGITUDE + "&descrip=" + stop.DESCRIPTION.replace("","+"), { headers: header });

    }

}
