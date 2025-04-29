package co.feip.fefu2025.presentation.ui.states.my_stars.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.presentation.ui.features.gitlabui.ui.GitLabCard
import co.feip.fefu2025.presentation.ui.features.gitlabui.ui.ShimmerGitLabCard
import co.feip.fefu2025.presentation.viewmodel.RepositoriesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoadingMyStarsPage(


    paddingValues: PaddingValues
    , modifier: Modifier = Modifier,
){


    Column(
        modifier =
        Modifier
            .padding(paddingValues),
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "All Projects",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        )

        LazyColumn(
            modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {


            items(10) { repo -> // Фильтруем репозитории

                ShimmerGitLabCard()


            }
        }

    }
}
