package com.example.medix

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager

var mapView: MapView? = null

class MapFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        mapView=view.findViewById(R.id.mapView)
        addAnnotationToMap(view.context,85.31473, 27.67338)
        addAnnotationToMap(view.context,85.31465, 27.67454)
//        addAnnotationToMap(view.context,85.31473, 27.67338)
//
//        addAnnotationToMap(view.context,85.31473, 27.67338)

        return view
    }

    private fun addAnnotationToMap(context: Context, longitude: Double, latitude: Double) {
// Create an instance of the Annotation API and get the PointAnnotationManager.
        bitmapFromDrawableRes(
            context,
            R.drawable.red_marker
        )?.let {
            val annotationApi = mapView?.annotations
            Log.d("TAG", "addAnnotationToMap: 1"+ mapView)

            Log.d("TAG", "addAnnotationToMap: 2"+ mapView?.annotations)

            val pointAnnotationManager = annotationApi?.createPointAnnotationManager(mapView!!)
// Set options for the resulting symbol layer.
            Log.d("TAG", "addAnnotationToMap: "+pointAnnotationManager)

            val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
// Define a geographic coordinate.
                .withPoint(Point.fromLngLat(85.31473, 27.67338))
// Specify the bitmap you assigned to the point annotation
// The bitmap will be added to map style automatically.
                .withIconImage(it)
// Add the resulting pointAnnotation to the map.
            Log.d("TAG", "addAnnotationToMap: "+pointAnnotationManager?.annotations?.size)
            pointAnnotationManager?.create(pointAnnotationOptions)
            Log.d("TAG", "addAnnotationToMap:1 "+pointAnnotationManager?.annotations?.size)

        }
    }

    //
    private fun bitmapFromDrawableRes(context: Context, @DrawableRes resourceId: Int) =
        convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId))

    private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {
        if (sourceDrawable == null) {
            return null
        }
        return if (sourceDrawable is BitmapDrawable) {
            sourceDrawable.bitmap
        } else {
// copying drawable object to not manipulate on the same reference
            val constantState = sourceDrawable.constantState ?: return null
            val drawable = constantState.newDrawable().mutate()
            val bitmap: Bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth, drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        }
    }
}




