import org.springframework.data.domain.Page

data class PageInfoResponseDto(
    val totalPages: Int,
    val totalElements: Long,
    val currentPage: Int,
) {
    companion object {
        fun of(page: Page<*>) : PageInfoResponseDto {
            return PageInfoResponseDto(
                totalPages = page.totalPages,
                totalElements = page.totalElements,
                currentPage = page.number,
            )
        }
    }
}
