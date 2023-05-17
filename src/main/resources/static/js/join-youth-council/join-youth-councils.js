(function(g) {
    var h, a, k, p = "The Google Maps JavaScript API",
        c = "google",
        l = "importLibrary",
        q = "__ib__",
        m = document,
        b = window;
    b = b[c] || (b[c] = {});
    var d = b.maps || (b.maps = {}),
        r = new Set,
        e = new URLSearchParams,
        u = () => h || (h = new Promise(async (f, n) => {
            await(a = m.createElement("script"));
            e.set("libraries", [...r] + "");
            for (k in g) e.set(k.replace(/[A-Z]/g, t => "_" + t[0].toLowerCase()), g[k]);
            e.set("callback", c + ".maps." + q);
            a.src = `https://maps.${c}apis.com/maps/api/js?` + e;
            d[q] = f;
            a.onerror = () => h = n(Error(p + " could not load."));
            a.nonce = m.querySelector("script[nonce]")?.nonce || "";
            m.head.append(a);
        }));
    d[l] ? console.warn(p + " only loads once. Ignoring:", g) : d[l] = (f, ...n) => r.add(f) && u().then(() => d[l](f, ...n));
})({
    key: "AIzaSyCOu0eUhpGD_i4JmMBeDJXTSm6233jjFXc",
    v: "weekly"
});

let map;
let openInfoWindow = null;

async function initMap() {
    await google.maps.importLibrary("places");

    // Create the map
    map = new google.maps.Map(document.getElementById("map"), {
        center: {lat: 51.0538, lng: 4.494},
        zoom: 9,
        restriction: {
            latLngBounds: {
                north: 51.475,
                south: 50.674,
                east: 5.924,
                west: 2.546
            },
            strictBounds: false
        },
        styles: [
            {
                featureType: "road",
                elementType: "geometry",
                stylers: [
                    {visibility: "off"}
                ]
            },
            {
                featureType: "road",
                elementType: "labels",
                stylers: [
                    {visibility: "off"}
                ]
            },
            {
                featureType: "administrative",
                elementType: "geometry.stroke",
                stylers: [
                    {visibility: "on"},
                    {color: "#000000"},
                    {weight: 1}
                ]
            },
            {
                featureType: "administrative",
                elementType: "labels.text.fill",
                stylers: [
                    {color: "#000000"}
                ]
            },
            {
                featureType: "administrative.province",
                elementType: "geometry.stroke",
                stylers: [
                    {visibility: "on"},
                    {color: "#000000"},
                    {weight: 2}
                ]
            },
            {
                featureType: "landscape",
                elementType: "all",
                stylers: [
                    {color: "#f2f2f2"}
                ]
            },
            {
                featureType: "poi",
                elementType: "all",
                stylers: [
                    {visibility: "off"}
                ]
            },
            {
                featureType: "water",
                elementType: "all",
                stylers: [
                    {color: "#b0d4e4"},
                    {visibility: "on"}
                ]
            }
        ]

    });

}
    async function renderMunicipalities(){
        const res = await fetch("/api/municipalities?p=true")
        if (res.status === 204) {
            //TODO: toast or notification no platforms
            return;
        }
        if(res.status === 500){
            //TODO: error sff
            return;}
        /**
         *
         * @type {
         * {name: string,
         * hasPlatform: boolean,
         * longitude: number,
         * latitude: number,
         * logo: string}[]
         * }
         */
        const municipalities = await res.json()
        return municipalities.forEach(municipality => createMarker(municipality))
    }


    function createMarker(municipality) {
        var marker = new google.maps.Marker({
            position: { lat: municipality.latitude, lng: municipality.longitude },
            map: map,
            title: municipality.name
        });

        var infoWindow = new google.maps.InfoWindow({
            //TODO: CONSTRUCT URL TO ACTUALLY GO TO THAT MUNICIPALITY
            content: municipality.name
        });

        marker.addListener("click", function() {
            if (openInfoWindow) {
                openInfoWindow.close(); // Close the previously opened info window
            }
            infoWindow.open(map, marker);
            openInfoWindow = infoWindow; // Update the currently opened info window
        });
    }

(async () => {
    await initMap()
    await renderMunicipalities()
})()
