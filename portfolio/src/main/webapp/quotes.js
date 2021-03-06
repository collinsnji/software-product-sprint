/**
 * Fetch quotes from the server and appends them to the page
 */
async function addQuotesToPage() {
  const serverResponse = await fetch('/quote');
  const responseJson = await serverResponse.json();

  // Get a random quote from the json quotes
  const quoteAuthor = responseJson.authorName;
  const quoteText = responseJson.quoteText;

  // Get the quote container and add the quote into it
  const quoteContainer = document.querySelector('.quote-container');
  quoteContainer.innerHTML = `"${quoteText}" - ${quoteAuthor}`;
};

// Execute the fetch once the DOM content is loaded and parsed
document.addEventListener('DOMContentLoaded', addQuotesToPage);
