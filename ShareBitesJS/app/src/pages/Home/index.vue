<template>
  <div>
    <div id="map"></div>
    <button class="button">
      <a :href="url" target="_blank">Learning</a>
    </button>
  </div>
</template>

<script>
import requests from '@/api/ajax';
import { format_time } from "@/utils/formatTime"
import { getToken } from "@/utils/token";
export default {
  data() {
    return {
      page: 1,
      size: 8,
      topics: [],
      map: null,
      position: {
        lat: "",
        lng: ""
      },
      url: "https://www.usda.gov/foodwaste/faqs",
      token: getToken(),
    };
  },
  mounted() {
    this.getCurrentInfo();
  },
  methods: {
    async initMap(lat, lng) {
      this.position = { lat, lng };
      this.getNearFoods();
      await google.maps.importLibrary("maps");
      google.maps.importLibrary("marker");

      this.map = new google.maps.Map(document.getElementById("map"), {
        center: new google.maps.LatLng(lat, lng),
        zoom: 16,
      });
      this.addMarker(lat, lng)
    },
    addMarker(lat, lng) {
      const icons = {
        currentPos: {
          icon: require("./images/location.png")
        }
      };
      const features = [
        {
          position: new google.maps.LatLng(lat, lng),
          type: "currentPos"
        }
      ];

      for (let i = 0; i < features.length; i++) {
        new google.maps.Marker({
          position: features[i].position,
          icon: icons[features[i].type].icon,
          map: this.map,
        });
      }
    },
    // marked with images
    addMarkerWithImage(lat, lng, img, name, id) {
      const contentString = `<img class="marker_img" src="${img}" alt="">`;
      const infowindow = new google.maps.InfoWindow({
        content: contentString,
        ariaLabel: name,
      });
      let marker = new google.maps.Marker({
        position: { lat, lng },
        map: this.map,
        title: name,
      });
      infowindow.open({
        anchor: marker,
        map: this.map,
      });
      marker.addListener("click", () => {
        this.$router.push(`/detail/${id}`);
      });
    },
    success(pos) {
      var crd = pos.coords;
      this.initMap(crd.latitude, crd.longitude);
    },
    getCurrentInfo() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(this.success, () => { }, { enableHighAccuracy: true, })
      } else {
        alert("Browser doesn't support Geolocation")
      }

    },
    getNearFoods() {
      let { lat, lng } = this.position;
      if (lat && lng) {
        requests(`/get/near/food?lng=${lng}&lat=${lat}`).then(res => {
          res.data.forEach(v => {
            this.addMarkerWithImage(v.latitude, v.longitude, v.image, v.name, v.id)
          })
        })
      }
    }
  }
};
</script>

<style scoped lang="less">
#map {
  height: calc(100vh - 70px);
  width: 100%;
  min-width: 1000px;
}

.button {
  position: fixed;
  left: 10px;
  bottom: 50px;
  height: 30px;
  line-height: 30px;
  width: 80px;
  font-size: 12px;
  z-index: 200;

  a {
    height: 100%;
    width: 100%;
  }
}

/deep/.marker_img {
  width: 100px;
  height: 80px;
  box-shadow: 0 0 10px rgba(0, 0, 0, .3);
  cursor: pointer;
}
</style>