import argparse
import os
from google.cloud import automl_v1beta1 as automl
project_id='xenon-lyceum-231515';
compute_region='us-west2';
dataset_name='happydb'

def create_dataset(project_id, compute_region, dataset_name, multilabel=False):
    """Create a dataset."""
    # [START automl_language_create_dataset]
    # TODO(developer): Uncomment and set the following variables
    # project_id = 'PROJECT_ID_HERE'
    # compute_region = 'COMPUTE_REGION_HERE'
    # dataset_name = 'DATASET_NAME_HERE'
    # multilabel = True for multilabel or False for multiclass

    from google.cloud import automl_v1beta1 as automl

    client = automl.AutoMlClient()

    # A resource that represents Google Cloud Platform location.
    project_location = client.location_path(project_id, compute_region)

    # Classification type is assigned based on multilabel value.
    classification_type = "MULTICLASS"
    if multilabel:
        classification_type = "MULTILABEL"

    # Specify the text classification type for the dataset.
    dataset_metadata = {"classification_type": classification_type}

    # Set dataset name and metadata.
    my_dataset = {
        "display_name": dataset_name,
        "text_classification_dataset_metadata": dataset_metadata,
    }

    # Create a dataset with the dataset metadata in the region.
    dataset = client.create_dataset(project_location, my_dataset)

    # Display the dataset information.
    print("Dataset name: {}".format(dataset.name))
    print("Dataset id: {}".format(dataset.name.split("/")[-1]))
    print("Dataset display name: {}".format(dataset.display_name))
    print("Text classification dataset metadata:")
    print("\t{}".format(dataset.text_classification_dataset_metadata))
    print("Dataset example count: {}".format(dataset.example_count))
    print("Dataset create time:")
    print("\tseconds: {}".format(dataset.create_time.seconds))
    print("\tnanos: {}".format(dataset.create_time.nanos))

    # [END automl_language_create_dataset]
    
create_dataset(project_id, compute_region, dataset_name, multilabel=False)