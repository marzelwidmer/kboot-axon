-- drop table if exists association_value_entry cascade;
-- drop table if exists domain_event_entry cascade;
-- drop table if exists saga_entry cascade;
-- drop table if exists snapshot_event_entry cascade;
-- drop table if exists token_entry cascade;
-- drop sequence if exists hibernate_sequence;

create sequence IF NOT EXISTS hibernate_sequence;
alter sequence hibernate_sequence owner to postgres;


create table IF NOT EXISTS association_value_entry
(
    id                int8         not null,
    association_key   varchar(255) not null,
    association_value varchar(255),
    saga_id           varchar(255) not null,
    saga_type         varchar(255),
    primary key (id)
);

create table IF NOT EXISTS domain_event_entry
(
    global_index         int8         not null,
    event_identifier     varchar(255) not null,
    meta_data            oid,
    payload              oid          not null,
    payload_revision     varchar(255),
    payload_type         varchar(255) not null,
    time_stamp           varchar(255) not null,
    aggregate_identifier varchar(255) not null,
    sequence_number      int8         not null,
    type                 varchar(255),
    primary key (global_index),
    constraint domain_event_aggregateIdentifier_sequenceNumber unique (aggregate_identifier, sequence_number),
    constraint domain_event_eventIdentifier unique (event_identifier)
);

create table IF NOT EXISTS saga_entry
(
    saga_id         varchar(255) not null,
    revision        varchar(255),
    saga_type       varchar(255),
    serialized_saga oid,
    primary key (saga_id)
);

create table IF NOT EXISTS snapshot_event_entry
(
    aggregate_identifier varchar(255) not null,
    sequence_number      int8         not null,
    type                 varchar(255) not null,
    event_identifier     varchar(255) not null,
    meta_data            oid,
    payload              oid          not null,
    payload_revision     varchar(255),
    payload_type         varchar(255) not null,
    time_stamp           varchar(255) not null,
    primary key (aggregate_identifier, sequence_number, type),
    constraint snapshot_event_eventIdentifier unique (event_identifier)
);

create table IF NOT EXISTS token_entry
(
    processor_name varchar(255) not null,
    segment        int4         not null,
    owner          varchar(255),
    timestamp      varchar(255) not null,
    token          oid,
    token_type     varchar(255),
    primary key (processor_name, segment)
);
