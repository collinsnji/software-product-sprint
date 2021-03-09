/**
 * Fetch quotes from the server and appends them to the page
 */
const addQuotesToPage = async () => {
    const serverResponse = await fetch('/quote');
    const responseText = await serverResponse.text();

    // Get the quote container and add the quote into it
    const quoteContainer = document.querySelector('.quote-container');
    quoteContainer.innerHTML = responseText;
}

// Execute the fetch once the DOM content is loaded and parsed
document.addEventListener("DOMContentLoaded", addQuotesToPage);
