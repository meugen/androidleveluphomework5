package ua.meugen.android.levelup.homework5.parcels;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;

/**
 * Created by meugen on 24.12.16.
 */

public class SparseBooleanArrayParcel implements Parcelable {

    public static final Creator<SparseBooleanArrayParcel> CREATOR = new Creator<SparseBooleanArrayParcel>() {
        @Override
        public SparseBooleanArrayParcel createFromParcel(final Parcel parcel) {
            return new SparseBooleanArrayParcel(parcel);
        }

        @Override
        public SparseBooleanArrayParcel[] newArray(final int size) {
            return new SparseBooleanArrayParcel[size];
        }
    };

    private final SparseBooleanArray array;

    public SparseBooleanArrayParcel(final SparseBooleanArray array) {
        this.array = array;
    }

    public SparseBooleanArrayParcel(final Parcel parcel) {
        final int count = parcel.readInt();
        final int[] keys = new int[count];
        parcel.readIntArray(keys);
        final boolean[] values = new boolean[count];
        parcel.readBooleanArray(values);

        this.array = new SparseBooleanArray(count);
        for (int i = 0; i < count; i++) {
            this.array.put(keys[i], values[i]);
        }
    }

    public SparseBooleanArray getArray() {
        return array;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int flags) {
        final int count = array.size();
        final int[] keys = new int[count];
        final boolean[] values = new boolean[count];
        for (int i = 0; i < count; i++) {
            keys[i] = array.keyAt(i);
            values[i] = array.valueAt(i);
        }

        parcel.writeInt(count);
        parcel.writeIntArray(keys);
        parcel.writeBooleanArray(values);
    }
}
