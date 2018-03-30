#!/bin/bash
rsync -auvz -e $WEBSITE_KEY /build/docs/ rsandor@atria.cs.odu.edu:./
rsync -auvz -e $WEBSITE_KEY /reports/ rsandor@atria.cs.odu.edu:./
