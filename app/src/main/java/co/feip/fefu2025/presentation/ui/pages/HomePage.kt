 package co.feip.fefu2025.presentation.ui.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.presentation.ui.features.all_projects_list.AllProjectsList
import co.feip.fefu2025.presentation.ui.features.my_stars_preview.MyStarsPreview
import co.feip.fefu2025.presentation.viewmodel.HomePageViewModel
import org.koin.androidx.compose.koinViewModel

 @RequiresApi(Build.VERSION_CODES.O)
 @Composable
 fun HomePage(viewModel: HomePageViewModel = koinViewModel()) {
     Scaffold(
         topBar = {
             Column(
                 modifier = Modifier
                     .windowInsetsPadding(WindowInsets.statusBars) // безопасный отступ сверху
             ) {
                 SearchBar(
                     value = "Search...",
                     onFocus = {
                         viewModel.navigateSearchScreen()
                     },
                     readOnly = true
                 )
             }
         },
     ) { paddingValues ->

         val listState = rememberLazyListState()

         Column(
         ) {
             Spacer(Modifier.height(paddingValues.calculateTopPadding()))

             MyStarsPreview(
                 navigateMyStars = {viewModel.navigateMyStars()},
                 navigateRepository = { id -> viewModel.navigateRepository(id) }
             )

             Spacer(modifier = Modifier.height(24.dp))

             AllProjectsList(
                 listState = listState,
                 navigateRepository = { id -> viewModel.navigateRepository(id)} ,
                 paddingValues =  paddingValues,
             )
         }
     }
 }
