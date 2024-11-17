package dev.theolm.pushalarm.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.theolm.pushalarm.R
import dev.theolm.pushalarm.ui.components.DefaultTopAppBar
import dev.theolm.pushalarm.ui.navigation.AddAlarmRoute
import dev.theolm.pushalarm.ui.navigation.LocalNavigator
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    viewModel: HomeScreenViewModel = koinViewModel(),
) {
    val navigator = LocalNavigator.current

    val scrollBarBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBarBehavior.nestedScrollConnection),
        topBar = {
            DefaultTopAppBar(
                title = stringResource(id = R.string.app_name),
                scrollBarBehavior = scrollBarBehavior,
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    navigator?.navigate(AddAlarmRoute)
                },
                shape = FloatingActionButtonDefaults.largeShape
            ) {
                Text("Add alarm")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding()),
            contentPadding = PaddingValues(16.dp)
        ) {
            repeat(50) {
                item {
                    ListItem(
                        headlineContent = { Text("Alarm $it") },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HomeScreen(
        viewModel = HomeScreenViewModel()
    )
}