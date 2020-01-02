package com.everneth.evermap.manager;

import com.everneth.evermap.App;
import com.everneth.evermap.utils.Utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.dynmap.DynmapAPI;
import org.dynmap.markers.Marker;
import org.dynmap.markers.MarkerAPI;
import org.dynmap.markers.MarkerSet;

public final class DynmapManager {
  private static DynmapManager dm;

  private Plugin plugin = App.getPlugin();
  private DynmapAPI dynmap = (DynmapAPI) plugin.getServer().getPluginManager().getPlugin("dynmap");
  private MarkerAPI markerapi = dynmap.getMarkerAPI();
  private MarkerSet baseMarkerSet;

  private DynmapManager() {
    if (baseMarkerSet == null) {
      if (markerapi.getMarkerSet("bases") != null) {
        baseMarkerSet = markerapi.getMarkerSet("bases");
      }
      baseMarkerSet = dynmap.getMarkerAPI().createMarkerSet("bases", "Playerbases",
          dynmap.getMarkerAPI().getMarkerIcons(), false);
      return;
    }
  }

  public static DynmapManager getDynmapManager() {
    if (dm == null) {
      dm = new DynmapManager();
    }

    return dm;
  }

  public void addMarker(Player player, String label, String desc) {
    Location loc = player.getLocation();
    Marker marker = baseMarkerSet.createMarker(player.getName() + "_base", label, false, desc, loc.getX(), loc.getY(),
        loc.getZ(), markerapi.getMarkerIcon("tower"), true);
    player.sendMessage(Utils.color("&3This is a start for marker command &4" + marker.getMarkerID() + " &8" + desc));
  }

  public void removeMarker(Player player, String label) {

    player.sendMessage(Utils.color("&2Remove marker command"));
  }
}