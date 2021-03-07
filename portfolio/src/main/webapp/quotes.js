/**
 * Fetch quotes from the server and appends them to the page
 */
async function addQuotesToPage() {
  const serverResponse = await fetch('/quote');
  const responseJson = await serverResponse.json();

  // Get a random quote from the json quotes
  const quote =
      responseJson
          .quotes[Math.floor(Math.random() * responseJson.quotes.length)];
  const quoteAuthor = quote.authorName;
  const quoteText = quote.quoteText;

  // Get the quote container and add the quote into it
  const quoteContainer = document.querySelector('.quote-container');
  quoteContainer.innerHTML = `"${quoteText}" - ${quoteAuthor}`;
};

// Execute the fetch once the DOM content is loaded and parse
document.addEventListener('DOMContentLoaded', addQuotesToPage);
