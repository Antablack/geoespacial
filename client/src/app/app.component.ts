import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from "@angular/material";
import * as L from 'leaflet';
import { STOPS } from "./models/stops";
import { stopsServices } from "./services/stops.services";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [stopsServices]
})
export class AppComponent implements OnInit {
  title = 'app';
  private map: L.Map;
  private marker: L.Marker;
  stops: Array<STOPS>;
  filter: Array<STOPS>;
  register: STOPS;
  markersLayer: L.LayerGroup = new L.LayerGroup();

  options = {
    layers: [
      L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', { maxZoom: 18, attribution: 'Geoespacial' })
    ],
    zoom: 15,
    center: L.latLng(2.9343405515242695, -75.28046607971193)
  };

  greenIcon = L.icon({
    iconUrl: './assets/marker-icon.png',
    shadowUrl: './assets/marker-shadow.png',
  });

  constructor(private _stopsServices: stopsServices, public snackbar: MatSnackBar) {


  }

  ngOnInit() {
  }
  onMapReady(map2: L.Map) {
    this.map = map2;


    this._stopsServices.getStop().subscribe((resu) => {
      this.stops = resu;
      this.filter = resu;
      this.drawMarker(resu);

    });

    /*  */
    this.map.on('click', (e: L.LeafletMouseEvent) => {

      if (this.marker != null) {
        this.markersLayer.removeLayer(this.marker);
      }
      this.marker = new L.Marker(e.latlng, { icon: this.greenIcon, draggable: true })
        .bindPopup("", {
          offset: L.point(12, 6)
        }).openPopup({ keepInView: true });
      //this.marker.addTo(this.map).openPopup({ keepInView: true });
      console.log(this.register);
      this.register = new STOPS();
      this.markersLayer.addLayer(this.marker);
      //this.markersLayer.addTo(this.map);
    })
  }


  drawMarker(stops: Array<STOPS>) {
    this.markersLayer.clearLayers();
    stops.forEach((e) => {
      let marker = new L.Marker(L.latLng(e.LATITUDE, e.LONGITUDE), { icon: this.greenIcon, draggable: true })
        .bindPopup(e.DESCRIPTION, {
          offset: L.point(12, 6)
        }).addTo(this.map).openPopup();
      this.markersLayer.addLayer(marker);
    })
    this.markersLayer.addTo(this.map);

  }

  guardar(descrip: String) {
    this.register.LATITUDE = this.marker._latlng.lat;
    this.register.LONGITUDE = this.marker._latlng.lng;
    this.register.DESCRIPTION = descrip;
    this._stopsServices.saveStops(this.register).subscribe((resu) => {
      if (resu.status == 200) {
        this.stops.push(this.register);
        this.filter = this.stops;
        this.register = undefined;
        this.snackbar.open("Guardado Exitoso", "", { duration: 2000 })
      }
    });
    this.marker._popup._content = descrip;
    this.marker = null;
  }

  buscar_keyup(event: any) {
    if (event.target.value != "") {
      this.filter = this.filter.filter((x) => x.DESCRIPTION.toLocaleLowerCase().indexOf(event.target.value.toLocaleLowerCase()) > -1);
      this.drawMarker(this.filter);
    } else {
      this.filter = this.stops;
      this.drawMarker(this.filter);
    }

  }

}
