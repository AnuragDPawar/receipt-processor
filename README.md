# Receipt Processor

This is a RESTful web service for processing receipts and calculating points based on specified rules.

## Features
- Process receipts and generate unique IDs.
- Retrieve points awarded for a given receipt ID.
- Fetch receipt with receipt ID

## Technologies Used
- Java 17
- Spring Boot
- Docker

## How to Run

### Prerequisites
- Docker installed on your system.

### Running Locally
1. Clone this repository:
   ```bash
   git clone  https://github.com/AnuragDPawar/receipt-processor.git
2. Build the docker image
   ```bash
   docker build -t receipt-processor .
3. Run the Docker container
   ```bash
   docker run -p 8080:8080 receipt-processor

