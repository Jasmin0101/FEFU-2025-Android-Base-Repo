package co.feip.fefu2025.presentation.ui.states.search_page.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.presentation.ui.features.gitlabui.ui.ShimmerGitLabCard

@Composable
fun LoadingSearchScreen(modifier: Modifier=Modifier){
    Column (modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),){
repeat(10){
       ShimmerGitLabCard()
}

    }

}