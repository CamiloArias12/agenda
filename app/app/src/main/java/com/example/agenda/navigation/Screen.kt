


sealed class Screen(val route: String) {
    object Main: Screen("main_screen")
    object View: Screen("view")
    object Edit: Screen("edit")
    object Create: Screen("create")

}