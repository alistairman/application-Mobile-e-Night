package com.example.enight

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.enight.dataBase.cour.Cour
import com.example.enight.dataBase.foodTruck.FoodTruck
import com.example.enight.dataBase.profile.Profile

@BindingAdapter("profileEmailText")
fun TextView.profileEmailText(item: Profile) {
    text = item.mail
}

@BindingAdapter("profileFirstNameText")
fun TextView.profileFirstNameText(item: Profile) {
    text = item.firstName
}

@BindingAdapter("profileLastNameText")
fun TextView.profileLastNameText(item: Profile) {
    text = item.lastName
}

@BindingAdapter("courNameTextView")
fun TextView.courNameText(item: Cour) {
    text = item.courId
}

@BindingAdapter("foodTruckNameTextView")
fun TextView.foodTruckNameText(item: FoodTruck) {
    text = item.location
}

/**@BindingAdapter("courNbCreditTextView")
fun TextView.courNbCreditText(item: Cour) {
    text = item.nbCredit.toString()+ " ECTS"
}

@BindingAdapter("courValidedTextView")
fun TextView.courValidedText(item: Cour) {
    text = if(item.valided) "OK"
    else "NOK"
}*/