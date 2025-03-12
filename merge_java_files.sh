#!/bin/bash

# Usage: $0 <directory>

DIRECTORY=$1
OUTPUT_FILE="generated_file_name.txt"

if [ -f "$OUTPUT_FILE" ]; then
    rm "$OUTPUT_FILE"
fi

find "$DIRECTORY" -type f -name "*.java" -print0 | while IFS= read -r -d '' file; do
    echo "===== $file =====" >>"$OUTPUT_FILE"
    cat "$file" >>"$OUTPUT_FILE"
    echo -e "\n" >>"$OUTPUT_FILE"
done

ZIP_FILE="generated_file_name.zip"
if [ -f "$ZIP_FILE" ]; then
    rm "$ZIP_FILE"
fi

zip "$ZIP_FILE" "$OUTPUT_FILE"
echo "All Java files have been merged and zipped into $ZIP_FILE"
