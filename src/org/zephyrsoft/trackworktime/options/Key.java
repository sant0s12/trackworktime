/*
 * This file is part of TrackWorkTime (TWT).
 * 
 * TWT is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * TWT is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with TWT. If not, see <http://www.gnu.org/licenses/>.
 */
package org.zephyrsoft.trackworktime.options;

import java.util.HashSet;
import java.util.Set;

import org.zephyrsoft.trackworktime.R;

/**
 * Central holder for all keys which are defined in XML files but have to be used in Java code.
 * 
 * @author Mathis Dirksen-Thedens
 */
public enum Key {

	ENABLE_FLEXI_TIME("keyEnableFlexiTime", DataType.BOOLEAN, null, R.string.enableFlexiTime),
	FLEXI_TIME_START_VALUE("keyFlexiTimeStartValue", DataType.HOUR_MINUTE, ENABLE_FLEXI_TIME,
		R.string.flexiTimeStartValue),
	FLEXI_TIME_TARGET("keyFlexiTimeTarget", DataType.HOUR_MINUTE, ENABLE_FLEXI_TIME, R.string.flexiTimeTarget),
	FLEXI_TIME_DAY_MONDAY("keyFlexiTimeDayMonday", DataType.BOOLEAN, ENABLE_FLEXI_TIME, R.string.mondayLong),
	FLEXI_TIME_DAY_TUESDAY("keyFlexiTimeDayTuesday", DataType.BOOLEAN, ENABLE_FLEXI_TIME, R.string.tuesdayLong),
	FLEXI_TIME_DAY_WEDNESDAY("keyFlexiTimeDayWednesday", DataType.BOOLEAN, ENABLE_FLEXI_TIME, R.string.wednesdayLong),
	FLEXI_TIME_DAY_THURSDAY("keyFlexiTimeDayThursday", DataType.BOOLEAN, ENABLE_FLEXI_TIME, R.string.thursdayLong),
	FLEXI_TIME_DAY_FRIDAY("keyFlexiTimeDayFriday", DataType.BOOLEAN, ENABLE_FLEXI_TIME, R.string.fridayLong),
	FLEXI_TIME_DAY_SATURDAY("keyFlexiTimeDaySaturday", DataType.BOOLEAN, ENABLE_FLEXI_TIME, R.string.saturdayLong),
	FLEXI_TIME_DAY_SUNDAY("keyFlexiTimeDaySunday", DataType.BOOLEAN, ENABLE_FLEXI_TIME, R.string.sundayLong),

	FLATTENING_ENABLED("keyFlatteningEnabled", DataType.BOOLEAN, null, R.string.flatteningEnabled),
	SMALLEST_TIME_UNIT("keySmallestTimeUnit", DataType.INTEGER, FLATTENING_ENABLED, R.string.smallestTimeUnit),

	LOCATION_BASED_TRACKING_ENABLED("keyLocationBasedTrackingEnabled", DataType.BOOLEAN, null,
		R.string.enableLocationBasedTracking),
	LOCATION_BASED_TRACKING_VIBRATE("keyLocationBasedTrackingVibrate", DataType.BOOLEAN,
		LOCATION_BASED_TRACKING_ENABLED, R.string.locationBasedTrackingVibrate),
	LOCATION_BASED_TRACKING_LATITUDE("keyLocationBasedTrackingLatitude", DataType.DOUBLE,
		LOCATION_BASED_TRACKING_ENABLED, R.string.workplaceLatitude),
	LOCATION_BASED_TRACKING_LONGITUDE("keyLocationBasedTrackingLongitude", DataType.DOUBLE,
		LOCATION_BASED_TRACKING_ENABLED, R.string.workplaceLongitude),
	LOCATION_BASED_TRACKING_TOLERANCE("keyLocationBasedTrackingTolerance", DataType.INTEGER,
		LOCATION_BASED_TRACKING_ENABLED, R.string.trackingTolerance),
	LOCATION_BASED_TRACKING_IGNORE_BEFORE_EVENTS("keyLocationBasedTrackingIgnoreBeforeEvents",
		DataType.INTEGER_OR_EMPTY, LOCATION_BASED_TRACKING_ENABLED, R.string.ignoreBefore),
	LOCATION_BASED_TRACKING_IGNORE_AFTER_EVENTS("keyLocationBasedTrackingIgnoreAfterEvents", DataType.INTEGER_OR_EMPTY,
		LOCATION_BASED_TRACKING_ENABLED, R.string.ignoreAfter),

	AUTO_PAUSE_ENABLED("keyAutoPauseEnabled", DataType.BOOLEAN, null, R.string.autoPauseEnabled),
	AUTO_PAUSE_BEGIN("keyAutoPauseBegin", DataType.TIME, AUTO_PAUSE_ENABLED, R.string.autoPauseBegin),
	AUTO_PAUSE_END("keyAutoPauseEnd", DataType.TIME, AUTO_PAUSE_ENABLED, R.string.autoPauseEnd),

	NOTIFICATION_ENABLED("keyNotificationEnabled", DataType.BOOLEAN, null, R.string.notificationEnabled),
	NOTIFICATION_USES_FLEXI_TIME_AS_TARGET("keyNotificationUsesFlexiTimeAsTarget", DataType.BOOLEAN,
		NOTIFICATION_ENABLED, R.string.notificationUsesFlexiTimeAsTarget),

	WIFI_BASED_TRACKING_ENABLED("keyWifiBasedTrackingEnabled", DataType.BOOLEAN, null, R.string.enableWifiBasedTracking),
	WIFI_BASED_TRACKING_VIBRATE("keyWifiBasedTrackingVibrate", DataType.BOOLEAN, WIFI_BASED_TRACKING_ENABLED,
		R.string.wifiBasedTrackingVibrate),
	WIFI_BASED_TRACKING_SSID("keyWifiBasedTrackingSSID", DataType.SSID, WIFI_BASED_TRACKING_ENABLED,
		R.string.workplaceWifiSSID);

	private final String name;
	private final DataType dataType;
	private final Key parent;
	private final Integer readableNameResourceId;

	private Key(String name, DataType dataType, Key parent, Integer readableNameResourceId) {
		this.name = name;
		this.dataType = dataType;
		this.parent = parent;
		this.readableNameResourceId = readableNameResourceId;
	}

	/**
	 * Get the name for use in SharedPreferences.getXXX(name, ...).
	 */
	public String getName() {
		return name;
	}

	public DataType getDataType() {
		return dataType;
	}

	public Key getParent() {
		return parent;
	}

	public Integer getReadableNameResourceId() {
		return readableNameResourceId;
	}

	public static Key getKeyWithName(String name) {
		for (Key key : values()) {
			if (key.getName().equals(name)) {
				return key;
			}
		}
		return null;
	}

	public static Set<Key> getChildKeys(Key parentKey) {
		Set<Key> ret = new HashSet<Key>();
		for (Key key : values()) {
			if (key.getParent() == parentKey) {
				ret.add(key);
			}
		}
		return ret;
	}

}
